public class Plan1571772779619 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

StartServer("C");

DecreaseTraffic("A");

}

} else {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

StartServer("C");

DecreaseTraffic("A");

}

}

}
}
