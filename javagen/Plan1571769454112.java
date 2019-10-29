public class Plan1571769454112 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("C") ) {
DecreaseDimmer("C");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

StartServer("B");
if ( StartServer("B") ) {
for (int i = 0; i < 8 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}




StartServer("B");

} else {
DecreaseDimmer("B");
StartServer("C");
StartServer("A");

StartServer("B");


if ( StartServer("B") ) {
if ( StartServer("A") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
for (int i = 0; i < 3 ; i++) {
IncreaseTraffic("B");
}

}

} else {
StartServer("B");
}

} else {
ShutdownServer("C");
}


}

}
}
