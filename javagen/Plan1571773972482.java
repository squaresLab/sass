public class Plan1571773972482 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
IncreaseTraffic("C");
}


}

for (int i = 0; i < 6 ; i++) {
StartServer("A");
}


}
}
