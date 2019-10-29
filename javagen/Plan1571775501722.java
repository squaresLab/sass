public class Plan1571775501722 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
IncreaseTraffic("C");
}

StartServer("B");

} else {
DecreaseTraffic("A");
}

}

}
}
