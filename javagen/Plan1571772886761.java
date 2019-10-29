public class Plan1571772886761 extends Plan { 
public static void main(String[] args) { 
IncreaseTraffic("A");
StartServer("C");

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
StartServer("A");
}

StartServer("B");
StartServer("A");


}


for (int i = 0; i < 3 ; i++) {
DecreaseDimmer("A");
}


}
}
