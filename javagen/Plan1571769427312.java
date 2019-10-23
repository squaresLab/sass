public class Plan1571769427312 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("A") ) {
StartServer("A");
} else {
StartServer("B");
}

}

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}


for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
DecreaseDimmer("B");
}

}

StartServer("B");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}




}
}
