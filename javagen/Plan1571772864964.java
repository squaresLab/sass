public class Plan1571772864964 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
DecreaseDimmer("A");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}


}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("A");

}


StartServer("C");

} else {

}

}

}
}
