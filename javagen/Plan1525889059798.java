public class Plan1525889059798 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}


}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("C");
}

}


}
}
