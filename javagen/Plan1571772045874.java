public class Plan1571772045874 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("A");
}

} else {
StartServer("A");
}

if ( StartServer("C") ) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

} else {
StartServer("B");
}


}

}
}
