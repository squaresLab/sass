public class Plan1571775265035 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("A");

}

if ( StartServer("B") ) {
if ( DecreaseDimmer("C") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("B");
}


}
}
