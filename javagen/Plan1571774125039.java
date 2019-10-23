public class Plan1571774125039 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
if ( StartServer("B") ) {
StartServer("C");
StartServer("B");

} else {
StartServer("B");
}


} else {
StartServer("C");
}

}

}
}
