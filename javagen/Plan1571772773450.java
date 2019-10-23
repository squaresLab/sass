public class Plan1571772773450 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
if ( ShutdownServer("A") ) {
IncreaseDimmer("A");
} else {
ShutdownServer("B");
}

}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}

DecreaseTraffic("A");

}


}
}
