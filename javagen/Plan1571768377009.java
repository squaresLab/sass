public class Plan1571768377009 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}


}

}
}
