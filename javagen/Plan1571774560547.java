public class Plan1571774560547 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
StartServer("B");

if ( StartServer("B") ) {
StartServer("B");
} else {
StartServer("B");
}


} else {
StartServer("B");
StartServer("C");
StartServer("B");

StartServer("A");


}

}

}
}
