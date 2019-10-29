public class Plan1571768656098 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
} else {
IncreaseTraffic("C");
}

StartServer("B");

DecreaseTraffic("A");

}

}
}
