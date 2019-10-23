public class Plan1571769958855 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("A");
}

StartServer("B");
StartServer("A");


StartServer("C");

}

}
}
