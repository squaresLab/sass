public class Plan1525889097662 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
if ( StartServer("B") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

} else {
ShutdownServer("A");
}


}

}

}
}
