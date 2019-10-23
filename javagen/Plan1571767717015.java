public class Plan1571767717015 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("B") ) {
StartServer("C");
} else {

}

} else {
StartServer("B");
}

DecreaseTraffic("A");

}

}
}
