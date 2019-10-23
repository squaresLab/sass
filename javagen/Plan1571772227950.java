public class Plan1571772227950 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}


}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");

}


}
}
