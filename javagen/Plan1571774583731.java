public class Plan1571774583731 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 5 ; i++) {
StartServer("C");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");

}


} else {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


}

} else {
if ( StartServer("C") ) {
StartServer("B");
for (int i = 0; i < 5 ; i++) {
StartServer("C");
StartServer("B");

}




} else {
ShutdownServer("B");
}

}

}

} else {
for (int i = 0; i < 4 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

}

}
}
