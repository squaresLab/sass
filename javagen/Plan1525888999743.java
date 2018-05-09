public class Plan1525888999743 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("B") ) {
for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("C");
StartServer("B");

}

DecreaseTraffic("A");

} else {
for (int i = 0; i < 2 ; i++) {
ShutdownServer("B");
}

}

StartServer("A");

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
ShutdownServer("C");
}

}


StartServer("B");

}
}
