public class Plan1571768760349 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
IncreaseTraffic("A");
}

StartServer("B");
StartServer("A");


}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
DecreaseTraffic("A");
}

StartServer("A");

}


}
}
