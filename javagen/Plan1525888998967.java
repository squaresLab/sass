public class Plan1525888998967 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("B") ) {
StartServer("C");
StartServer("C");

if ( StartServer("B") ) {
if ( IncreaseDimmer("C") ) {
if ( StartServer("A") ) {
ShutdownServer("A");
} else {
DecreaseDimmer("C");
}

} else {

}

} else {
for (int i = 0; i < 2 ; i++) {
IncreaseDimmer("C");
}

}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
}



} else {
DecreaseTraffic("B");
}

StartServer("C");

}
}
