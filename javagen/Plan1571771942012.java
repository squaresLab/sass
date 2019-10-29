public class Plan1571771942012 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("B") ) {
if ( IncreaseTraffic("C") ) {
StartServer("C");
} else {
StartServer("A");
}

} else {
DecreaseTraffic("A");
}

} else {
StartServer("A");
}

}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

}


}
}
