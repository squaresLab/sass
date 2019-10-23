public class Plan1571772369353 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}

if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

DecreaseTraffic("A");

if (  ) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
if ( ShutdownServer("A") ) {
ShutdownServer("A");
} else {
IncreaseDimmer("A");
}

}

DecreaseTraffic("A");

} else {
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

}



}
}
