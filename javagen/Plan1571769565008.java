public class Plan1571769565008 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

}

} else {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("C");
}

}

DecreaseTraffic("A");

}

}
}
