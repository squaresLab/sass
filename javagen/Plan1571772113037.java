public class Plan1571772113037 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


DecreaseTraffic("A");

if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {

}


}

} else {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


DecreaseTraffic("A");

if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {

}


}

} else {
DecreaseTraffic("C");
}

}

}
}
