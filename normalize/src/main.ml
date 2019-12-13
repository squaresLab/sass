open Sexplib
open Sexp

let debug = false

let read () =
  let rec aux acc =
    try aux ((input_line stdin) ^ acc)
    with | End_of_file -> acc
  in
  aux ""

type 'a node =
  | For of int * 'a node
  | Seq of 'a node * 'a node
  | T of 'a node * 'a node * 'a node
  | Leaf of string * string

let rec parse = function
  | List [] -> assert false
  | List (Atom "F"::Atom count::node::[]) ->
    let count = int_of_string (Char.escaped (String.get count 5)) in
    For (count, parse node)
  | List (Atom ";"::left::right::[]) -> Seq (parse left, parse right)
  | List (Atom "T"::left::mid::right::[]) -> T (parse left, parse mid, parse right)
  | List (Atom t::Atom s::[]) -> Leaf (t,s)
  | x ->
    Format.printf "FAILED TO PARSE %s@." (Sexp.to_string x);
    assert false

let reduced_nodes = ref 0

let rec pp_node : 'a node -> unit = function
  | For (c, node) -> Format.printf "(F ERC[i%d|] " c; pp_node node; Format.printf ")"
  | Seq (left, right) -> Format.printf "(; "; pp_node left; Format.printf " "; pp_node right; Format.printf ")"
  | T (left, mid, right) -> Format.printf "(T "; pp_node left; Format.printf " "; pp_node mid; Format.printf " "; pp_node right; Format.printf ")"
  | Leaf (left, right) -> Format.printf "(%s %s)" left right

let rec reduce : 'a node -> 'a node = function
  | For (c, (For (c2, node))) ->
    reduced_nodes := !reduced_nodes + 1;
    reduce (For ((c+c2), reduce node))

  | Seq (Seq (other, node1), node2)
  | Seq (node2, Seq (other, node1))
    when node1 = node2 ->
    reduced_nodes := !reduced_nodes + 1;
    reduce (Seq (reduce other, reduce (For (2, node1))))

  | Seq (Seq (node1, other), node2)
  | Seq (node2, Seq (node1, other))
    when node1 = node2 ->
    reduced_nodes := !reduced_nodes + 1;
    reduce (Seq (reduce other, reduce (For (2, node1))))

  | Seq (left, right) when left = right ->
    reduced_nodes := !reduced_nodes + 1;
    reduce (For (2, reduce left))

  | Seq (For (c, node), node2)
  | Seq (node2, (For (c, node)))
    when node = node2 ->
    reduced_nodes := !reduced_nodes + 1;
    reduce (For (c+1, reduce node))

  | n -> n

let () =
  let input = read () in
  if debug then Format.printf "%s@." input;
  let sexp = Sexp.of_string input in
  if debug then Format.printf "Sexp:@.";
  if debug then Format.printf "%s@." (Sexp.to_string sexp);
  let parsed = parse sexp in
  if debug then Format.printf "Parsed:@.";
  if debug then pp_node parsed;
  if debug then Format.printf "@.";
  let reduced = reduce parsed in
  if debug then Format.printf "Reduced:@.";
  Format.eprintf "Reduced %d nodes@." !reduced_nodes;
  pp_node reduced
