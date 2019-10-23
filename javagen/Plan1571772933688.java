public class Plan1571772933688 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


StartServer("C");

if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}

StartServer("A");

StartServer("C");



}

}
}
