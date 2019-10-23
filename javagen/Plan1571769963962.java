public class Plan1571769963962 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}

}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
DecreaseTraffic("C");
}

DecreaseDimmer("A");

}


}
}
