public class Plan1571775490698 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}

if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("A");
}


}

}
}
