public class Plan1571768445057 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("B");
}




if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}


}

}
}
