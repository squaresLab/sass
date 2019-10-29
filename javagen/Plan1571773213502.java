public class Plan1571773213502 extends Plan { 
public static void main(String[] args) { 
if ( IncreaseTraffic("A") ) {
if ( IncreaseDimmer("A") ) {
IncreaseDimmer("C");
} else {
DecreaseDimmer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
DecreaseTraffic("A");

}

}

if ( StartServer("C") ) {
IncreaseTraffic("C");
} else {
IncreaseTraffic("C");
}

StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("B");

}



for (int i = 0; i < 2 ; i++) {
if ( DecreaseDimmer("C") ) {
ShutdownServer("A");
} else {
StartServer("B");
}

}



}
}
