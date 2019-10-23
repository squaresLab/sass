public class Plan1571768620249 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
DecreaseDimmer("A");

} else {
StartServer("A");
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}

StartServer("B");


}

}

}
}
