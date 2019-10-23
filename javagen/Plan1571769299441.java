public class Plan1571769299441 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( StartServer("C") ) {
StartServer("C");
DecreaseTraffic("A");

} else {
StartServer("A");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}


}
}
