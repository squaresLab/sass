public class Plan1571770113891 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("A");
}

StartServer("B");

StartServer("C");

}

}
}
