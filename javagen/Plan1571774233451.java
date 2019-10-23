public class Plan1571774233451 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}

DecreaseTraffic("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
