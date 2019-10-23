public class Plan1571768010601 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
StartServer("A");
}

}


}

}
}
