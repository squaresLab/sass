public class Plan1571769479901 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

}

} else {
StartServer("A");
}



if ( DecreaseTraffic("A") ) {
if (  ) {
IncreaseDimmer("C");
} else {
StartServer("C");
}

} else {
ShutdownServer("C");
}


}

}
}
