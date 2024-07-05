package currencyapi;

public class Currency {
        private String symbol;
        private String name;
        private String symbol_native;
        private long decimal_digits;
        private long rounding;
        private String code;
        private String name_plural;
        private String type;
    
        public String getName(){
            return this.name;
        }
    
        public String getCode(){
            return this.code;
        }
}
