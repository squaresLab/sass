public class Plan1525889097119 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("C") ) {
ShutdownServer("B");
} else {
StartServer("A");
}

if ( StartServer("B") ) {
if ( StartServer("C") ) {
StartServer("A");
} else {
DecreaseDimmer("A");
}

} else {
StartServer("A");
}


}

}
}
