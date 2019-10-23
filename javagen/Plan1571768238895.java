public class Plan1571768238895 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
StartServer("C");
StartServer("B");


if ( StartServer("A") ) {
StartServer("C");
DecreaseDimmer("A");

} else {
StartServer("A");
}


}

StartServer("A");

}
}
