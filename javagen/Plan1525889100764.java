public class Plan1525889100764 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
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
