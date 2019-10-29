public class Plan1571774640818 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
IncreaseTraffic("B");
}



}


}
}
