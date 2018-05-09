public class Plan1525889210682 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

StartServer("C");
if ( StartServer("B") ) {
DecreaseDimmer("B");
} else {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
ShutdownServer("B");
}

}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseDimmer("A");
}

}




}
}
