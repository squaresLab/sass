public class Plan1571775565095 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}


}

}
}
