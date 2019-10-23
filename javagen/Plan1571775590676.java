public class Plan1571775590676 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 6 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

}

for (int i = 0; i < 5 ; i++) {
StartServer("C");
}



}
}
