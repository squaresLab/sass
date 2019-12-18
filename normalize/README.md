##

Producing the before-after file I sent cody on slack:

Download and install `opam`: https://opam.ocaml.org/doc/Install.html. Usually this means running:

```
sh <(curl -sL https://raw.githubusercontent.com/ocaml/opam/master/shell/install.sh)
```

Wait for opam to finish, it takes some time because it's compiling the compiler. Then:

- `opam install dune sexplib`
- `eval $(opam config env)`
- In this directory, type `make`

now `cd` into the `repertoire` folder. Then:

- run `../../normalize/minimize.sh`

What you'll see is a bunch of messages saying `Reduced n nodes` where n is a `0` or a `1` or sometimes more. You can ignore this output by piping to stderr (.`../../nomralize/minimize.sh 2> /dev/null`)

After the script finishes, it will diff the files so we can see what was minimized. It dumps this output on stdout, and can be redirected to to the file `../../nomralize/minimize.sh > before-after`. It also _changes the repertoire in place_, so all those repertoire files are now minimized. To get them back, type `git checkout -- .` in the repertoire folder, which resets the file state to the data that is versioned in the last commit.
