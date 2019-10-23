public class Plan1571767895330 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("C");
}


}


}
}
