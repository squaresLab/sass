public class Plan1571770546074 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

StartServer("C");
DecreaseTraffic("A");


}

}
}
