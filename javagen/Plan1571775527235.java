public class Plan1571775527235 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

} else {
StartServer("B");
}

if ( DecreaseDimmer("C") ) {
StartServer("C");
StartServer("A");

StartServer("B");

} else {
StartServer("B");
StartServer("C");
StartServer("A");


StartServer("B");

}


}

}
}
