public class Plan1571773267794 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("B");
}

if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("C");
}


}

}
}
