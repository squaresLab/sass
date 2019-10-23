public class Plan1571773447466 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

} else {
StartServer("C");
}

}

}

}
}
