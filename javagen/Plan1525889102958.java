public class Plan1525889102958 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
DecreaseDimmer("A");
if ( StartServer("B") ) {
StartServer("C");
StartServer("A");

} else {
StartServer("C");
}



}

StartServer("B");


}
}
