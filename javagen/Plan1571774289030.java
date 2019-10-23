public class Plan1571774289030 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("A");
}

StartServer("A");


}

}
}
