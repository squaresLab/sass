public class Plan1571768819163 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( IncreaseTraffic("A") ) {
IncreaseDimmer("B");
} else {
DecreaseTraffic("A");
StartServer("A");

}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}



}
}
