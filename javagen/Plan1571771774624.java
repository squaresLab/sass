public class Plan1571771774624 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("C");

}

}

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
DecreaseDimmer("A");
}

StartServer("A");


}

}
}
