public class Plan1525889074908 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

DecreaseDimmer("C");
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

StartServer("C");



for (int i = 0; i < 3 ; i++) {
StartServer("C");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseTraffic("A");
}


}


}
}
